# 学途管家 — Docker 一键部署

本项目使用 Docker Compose 完成**前端 + 后端 + MySQL + Redis** 的容器化一键部署，无需本机安装任何数据库或中间件。

## 一、部署架构

```
浏览器
  │
  ▼ :90
nginx 容器（jm-nginx）──────────────► 前端静态页面（front-dist，Vue 打包产物）
  │  /api/* 去掉前缀反代
  ▼ :8080
Spring Boot 后端容器（jm-app，多阶段 Maven 构建出 jar）
  │                        │
  ▼ :3306                  ▼ :6379
MySQL 8 容器              Redis 7 容器
(jm-mysql，首次启动自动    (jm-redis，缓存/分布式锁/
 执行 docker/mysql/init     操作日志异步队列)
 下的 qk.sql 建库建表)
```

## 二、前置要求

- Docker Desktop for Windows（WSL2 后端），下载：https://www.docker.com/products/docker-desktop/
- 宿主机 90、8080、3307、6380 端口未被占用（若本机 nginx / 后端在运行请先停止）

## 三、一键部署

```bash
# 在项目根目录（jm-parent）执行
docker compose up -d --build
```

首次构建说明：
- 后端镜像采用**多阶段构建**：Maven 容器内编译多模块项目（阿里云镜像源加速），产出 `jm-management` 可执行 jar 后放入 JRE 17 镜像运行
- MySQL 容器首次启动会自动执行 `docker/mysql/init/qk.sql`，完成建库（`cpjm-parent`）、建表与示例数据初始化
- 全部启动约需 1~2 分钟，可用以下命令观察后端就绪状态：

```bash
docker compose logs -f app     # 看到 "Started JmManagementApplication" 即启动成功
```

## 四、验证部署

| 项目 | 地址 | 预期结果 |
|---|---|---|
| 前端页面 | http://localhost:90 | 显示学途管家登录页 |
| 后端接口 | http://localhost:90/api/dept/list | 返回部门数据 JSON |
| 后端直连 | http://localhost:8080 | 404（无根路径路由，属正常） |

使用默认账号登录系统（如 `zhangsan / 123`，见 qk.sql 中 user 表初始数据）。

## 五、常用命令

```bash
docker compose ps              # 查看容器状态
docker compose logs -f app     # 查看后端日志
docker compose restart app     # 重启后端
docker compose down            # 停止并删除容器（数据卷保留）
docker compose down -v         # 停止并清空数据库/缓存数据（重新初始化）
docker compose up -d --build   # 修改代码后重新构建部署
```

## 六、目录说明

```
├── Dockerfile                  # 后端镜像（Maven 构建 + JRE 运行 多阶段）
├── docker-compose.yml          # 四个容器的编排定义
├── .dockerignore
├── docker/
│   ├── nginx.conf              # 前端容器配置（静态资源 + /api 反代）
│   ├── maven-settings.xml      # 构建用阿里云 Maven 镜像源
│   └── mysql/init/qk.sql       # MySQL 首次启动自动执行的初始化脚本
└── front-dist/                 # 前端 Vue 打包产物（nginx 容器托管）
```

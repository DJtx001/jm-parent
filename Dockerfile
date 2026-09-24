# ============================================================
# 学途管家后端镜像（多阶段构建）
# 阶段一：Maven 构建多模块项目，产出 jm-management 可执行 jar
# 阶段二：JRE 17 运行
# ============================================================

# ---------- 阶段一：构建 ----------
FROM maven:3.9-eclipse-temurin-17 AS builder
WORKDIR /build

# 使用阿里云 Maven 镜像源加速依赖下载（配置见 docker/maven-settings.xml）
# 利用 BuildKit 缓存挂载持久化本地仓库，代码变更时无需重复下载依赖
COPY . .
RUN --mount=type=cache,target=/root/.m2 \
    mvn -s docker/maven-settings.xml clean package -DskipTests -q

# ---------- 阶段二：运行 ----------
FROM eclipse-temurin:17-jre
WORKDIR /app

COPY --from=builder /build/jm-management/target/jm-management-*.jar app.jar

ENV TZ=Asia/Shanghai
EXPOSE 8080

ENTRYPOINT ["java", "-Duser.timezone=Asia/Shanghai", "-jar", "app.jar"]

# ─── Stage 1: Build ───────────────────────────────────────────────────────────
FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /app

# Copy source files
COPY src/ src/

# Compile
RUN mkdir -p out && \
    find src -name "*.java" > sources.txt && \
    javac -d out @sources.txt

# Package JAR with manifest
RUN echo "Main-Class: com.bankofkigali.LoanSystem" > manifest.txt && \
    echo "" >> manifest.txt && \
    jar cfm BankOfKigali-LMS.jar manifest.txt -C out .

# ─── Stage 2: Runtime ─────────────────────────────────────────────────────────
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=builder /app/BankOfKigali-LMS.jar .

# Run the application
CMD ["java", "-jar", "BankOfKigali-LMS.jar"]

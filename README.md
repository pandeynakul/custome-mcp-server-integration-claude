📌 Overview
This project is a Spring Boot microservice that implements a custom MCP (Model Context Protocol) server for managing a shopping cart.
It uses PostgreSQL (via pgAdmin) for persistence and can integrate with Claude Desktop through MCP configuration.

⚙️ Prerequisites
Java 17+
Maven/Gradle
PostgreSQL with pgAdmin installed
Claude Desktop (latest version)
 VS Code or IntelliJ IDEA for editing

 Claude Desktop Integration
Claude Desktop reads configuration from a hidden folder named .claude_desktop in the user’s home directory.
Create a file named claude_desktop_config.json inside this folder.
Define your MCP server with command, arguments, environment variables, and transport type (stdio or SSE).
Restart Claude Desktop after saving the configuration to load the server.

Notes
Use stdio transport for local desktop integration.
Use SSE transport if you want Claude to connect over HTTP.
Always restart Claude Desktop after editing the configuration file.
Ensure PostgreSQL schema matches your entity definitions to avoid runtime errors.

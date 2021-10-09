# Segundo Proyecto Programado #

## Documentación técnica ##

### Instalación de herramientas ###

**Se presupone que estará trabajando en un ambiente linux**.

Instalar SDKMAN! https://sdkman.io/install

Instalar Java 11

```bash
sdk install java 11.0.8.hs-adpt
```

### Variables de ambiente ###

Variables de ambiente a definir, con valores de ejemplo. Se recomienda utilizar la herramienta *direnv* y definir la variables en un archivo `.envrc`.

```bash
export POSTGRES_PASSWORD=root
export APP_DB_PSWD=app
export APP_DB_USER=app
export APP_DB_NAME=app
export APP_AUTH_JWT_SECRET=b898c01c6
```

### Construir y ejecutar ###

Para construir la aplicación y correr las pruebas utilice el comando:

```bash
./gradlew clean build
```

Para ejecutar la aplicación utilice el comando:

```bash
./gradlew bootRun
```

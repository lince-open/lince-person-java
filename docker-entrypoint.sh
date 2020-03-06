#!/bin/sh
set -eux

if [ x"${LINCE_PERSON_JAVA_OPT:-}" = x ]; then
  LINCE_PERSON_JAVA_OPT="-Xms448m -Xmx448m -XX:+UseG1GC -XX:+ExitOnOutOfMemoryError -XX:+CrashOnOutOfMemoryError -Dspring.profiles.active=prod -Djava.security.egd=file:/dev/./urandom"
fi

if [ x"${LINCE_PERSON_PORT:-}" = x ]; then
  LINCE_PERSON_PORT=${PORT:-8080}
  export LINCE_PERSON_PORT
fi

exec java ${LINCE_PERSON_JAVA_OPT} -Dspring.profiles.active=prod -jar /app.jar

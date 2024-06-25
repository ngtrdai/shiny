import os

from pydantic.v1 import BaseSettings


class Settings(BaseSettings):
    KEYCLOAK_URL: str = os.getenv("KEYCLOAK_URL")
    KEYCLOAK_REALM: str = os.getenv("KEYCLOAK_REALM")
    KEYCLOAK_CLIENT_ID: str = os.getenv("KEYCLOAK_CLIENT_ID")
    KEYCLOAK_CLIENT_SECRET: str = os.getenv("KEYCLOAK_CLIENT_SECRET")
    
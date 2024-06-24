import uvicorn
from fastapi import FastAPI

from constants import app_constant
from routers import health_check

app = FastAPI(
    title=app_constant.APP_NAME,
    description=app_constant.APP_DESC
)

idp = FastAPIKeyCloak(app, "keycloak.json")


app.include_router(health_check.router, prefix=app_constant.API_V1_PREFIX)


if __name__ == "__main__":
    uvicorn.run("main:app", host="0.0.0.0", port=8000, log_level="info", reload=True)

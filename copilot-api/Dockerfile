FROM python:3.10-slim-buster

LABEL maintainer="Dai Nguyen Trong <ngtrdai@hotmail.com>"

RUN apt-get update \
    && apt-get install -y curl
RUN curl -sSL https://install.python-poetry.org | python3 -

ENV PATH="${PATH}:/root/.local/bin"

RUN poetry config virtualenvs.create false

WORKDIR /app

COPY pyproject.toml poetry.lock ./

RUN poetry install --no-root --no-dev

COPY . .

CMD ["poetry", "run", "uvicorn", "app.main:app", "--host", "0.0.0.0", "--port", "8000"]
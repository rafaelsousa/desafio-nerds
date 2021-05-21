FROM node

RUN npm install -g @vue/cli

COPY ./start.sh /start.sh
RUN chmod +x /start.sh

WORKDIR /app

ENTRYPOINT [ "/start.sh" ]
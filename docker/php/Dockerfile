FROM php:8.0-fpm-alpine

RUN apk --no-cache add postgresql-dev && \
    docker-php-ext-configure opcache --enable-opcache && \
    docker-php-ext-install pdo pdo_pgsql opcache

RUN curl -sS https://getcomposer.org/installer | php -- --install-dir=/usr/bin --filename=composer

# Use the default production configuration
COPY ./php-fpm.conf /usr/local/etc/php-fpm.d/www.conf
COPY ./opcache.ini $PHP_INI_DIR/conf.d/opcache.ini-prod
COPY ./start.sh /start.sh
RUN chmod +x /start.sh

WORKDIR /var/www

ENTRYPOINT [ "/start.sh" ]

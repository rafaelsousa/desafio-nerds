FROM php:8.0-fpm-alpine

RUN apk --no-cache add postgresql-dev && \
    docker-php-ext-configure opcache --enable-opcache && \
    docker-php-ext-install pdo pdo_pgsql opcache

# Use the default production configuration
RUN mv "$PHP_INI_DIR/php.ini-production" "$PHP_INI_DIR/php.ini"
COPY ./php-fpm.conf /usr/local/etc/php-fpm.d/www.conf
COPY ./opcache.ini $PHP_INI_DIR/conf.d/opcache.ini

WORKDIR /var/www

ENTRYPOINT [ "php-fpm" ]

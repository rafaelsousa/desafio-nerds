#!/usr/bin/env sh

ENVIRONMENT=${ENVIRONMENT:="development"}

if [ ! -d "./vendor" ]; then
    composer install
fi

export DEGUG=true;
if [ "$ENVIRONMENT" = "production" ]; then
    export DEGUG=false;
    mv $PHP_INI_DIR/conf.d/opcache.ini-prod $PHP_INI_DIR/conf.d/opcache.ini
    mv $PHP_INI_DIR/php.ini-production $PHP_INI_DIR/php.ini
fi

sleep 5
php artisan migrate --force

php-fpm

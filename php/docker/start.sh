#!/usr/bin/env sh

ENVIRONMENT=${ENVIRONMENT:="development"}

if [ ! -d "./vendor" ]; then
    composer install
fi

export DEGUG=true;
if [ "$ENVIRONMENT" = "production" ]; then
    export DEGUG=false;
    mv $PHP_INI_DIR/conf.d/opcache.ini-prod $PHP_INI_DIR/conf.d/opcache.ini
fi

mv $PHP_INI_DIR/php.ini-$ENVIRONMENT $PHP_INI_DIR/php.ini

php-fpm

<?php

/** @var \Laravel\Lumen\Routing\Router $router */

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It is a breeze. Simply tell Lumen the URIs it should respond to
| and give it the Closure to call when that URI is requested.
|
*/

$router->get('/', function () use ($router) {
    return $router->app->version();
});

$router->group(["prefix" => "person"], function () use ($router) {
    $router->get('/', ['uses' => 'PersonController@index']);
    $router->get('/{id}', ['uses' => 'PersonController@show']);
    $router->post('/', ['uses' => 'PersonController@store']);
    $router->put('/{id}', ['uses' => 'PersonController@update']);
    $router->delete('/{id}', ['uses' => 'PersonController@destroy']);

    $router->get('/{person}/address', ['uses' => 'AddressController@index']);
    $router->get('/{person}/address/{id}', ['uses' => 'AddressController@show']);
    $router->post('/{person}/address', ['uses' => 'AddressController@store']);
    $router->put('/{person}/address/{id}', ['uses' => 'AddressController@update']);
    $router->delete('/{person}/address/{id}', ['uses' => 'AddressController@destroy']);

    $router->get('/{person}/telephone', ['uses' => 'TelephoneController@index']);
    $router->get('/{person}/telephone/{id}', ['uses' => 'TelephoneController@show']);
    $router->post('/{person}/telephone', ['uses' => 'TelephoneController@store']);
    $router->put('/{person}/telephone/{id}', ['uses' => 'TelephoneController@update']);
    $router->delete('/{person}/telephone/{id}', ['uses' => 'TelephoneController@destroy']);
});

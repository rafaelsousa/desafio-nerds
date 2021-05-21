#!/usr/bin/env bash

if [ ! -d "node_modules" ]; then
  npm install
fi

echo "start server"
npm run serve
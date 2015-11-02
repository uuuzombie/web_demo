#! /bin/bash

echo "====> drop database"
sudo -u postgres dropdb test_db

echo "====> drop user"
sudo -u postgres dropuser dbuser
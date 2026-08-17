docker run --rm \
--shm-size=2g \
-e SUITE="allure.xml" \
-e TEST_USERNAME="tomsmith" \
-e TEST_PASSWORD="SuperSecretPassword!" \
-v "$(pwd)/allure-results:/app/allure-results" \
selenium-tests
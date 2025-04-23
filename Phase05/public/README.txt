In order to run the test cases, you must run this command to install jest

    npm install --save-dev @testing-library/jest-dom@latest

Also, to run the test cases, you should be in the "public" directory or the "__tests__" directoery when issuing this command

    npm test

If there is an issue, try running these commands

    npm install --save-dev @testing-library/jest-dom@latest
    npx jest --clearCache
    npm test

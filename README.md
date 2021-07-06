# revature-project2-group4-BlissfulIsland

## Contributors
| Name          | Role         |
|---------------|--------------|
| Robert Goette | Team Lead    |
| Boston Bragg  | Team Member  |
| Carlos Herrera| Team Member  |
| Mohammad Asif | Team Member  |

## Description
This is the server for the BlissfulIsland tenant-manager messaging system. This backend handles the requests from the [front end](https://2105batch-project2-group4.s3.us-east-2.amazonaws.com/index.html).

## Technologies and Services Used
- Java, Javalin, PostgreSQL, Postman, AWS 

## Routes 
- get "/accounts"  getAllAccounts
- get "/accounts/manager" getAllManagerAccounts
- get "/accounts/tenant" getAllTenantAccounts
- get "/accounts/:id" getAccountById
- patch "/accounts/:id" updateAccount
- delete "/accounts/:id" deleteAccount

- post "/login" loginController.login

- get "/messages" getAllMessages
- get "/messages/:id" getMessageById
- post "/messages" createMessage

- get "/units" getAllUnits
- get "/units/:id" getUnitById

- get "/types" getAllUnitTypes
- get "/types/:id" getUnitTypeById
- get "/typesAvailability" getAvailableUnitTypes

# order-service
Spring Microservice

# port
9090

## GET
curl -X GET \
  http://localhost:9090/orders/1 \
  -H 'cache-control: no-cache' \
  -H 'postman-token: 65cf03a0-b9f1-537b-ed10-399e89952250'
  
  ## POST
  curl -X POST \
  http://localhost:9090/orders \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 53907dce-6c49-640d-5eda-5604c39505ca' \
  -d '{
    "id": 2,
    "email": "exemplo@exemplo.com",
    "fullName": "Nome Exemplo POST",
    "shippingAddress": "Rua Exemplo, 23, Exemplo-Ex",
    "products": [
        {
            "idOrder": 1,
            "description": "Item A0",
            "value": 52.33,
            "amount": 1
        },
        {
            "idOrder": 1,
            "description": "Item A1",
            "value": 104.66,
            "amount": 1
        },
        {
            "idOrder": 1,
            "description": "Item A2",
            "value": 156.99,
            "amount": 1
        },
        {
            "idOrder": 1,
            "description": "Item A3",
            "value": 209.32,
            "amount": 1
        }
    ],
    "payment": {
        "id": 1,
        "cardNumber": "1234 4321 4567 7654",
        "expiringDate": "02/1922",
        "cardFlag": "Visa"
    },
    "date": "22/02/1993",
    "status": "Aprovado",
    "total": 523.3
}'

## PUT
curl -X PUT \
  http://localhost:9090/orders \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 6571c7ea-b194-0d6a-6943-36a7a8491c0b' \
  -d '{
    "id": 2,
    "email": "exemplo@exemplo.com",
    "fullName": "Nome Exemplo PUT",
    "shippingAddress": "Rua Exemplo, 23, Exemplo-Ex",
    "products": [
        {
            "idOrder": 1,
            "description": "Item A0",
            "value": 52.33,
            "amount": 1
        },
        {
            "idOrder": 1,
            "description": "Item A1",
            "value": 104.66,
            "amount": 1
        },
        {
            "idOrder": 1,
            "description": "Item A2",
            "value": 156.99,
            "amount": 1
        },
        {
            "idOrder": 1,
            "description": "Item A3",
            "value": 209.32,
            "amount": 1
        }
    ],
    "payment": {
        "id": 1,
        "cardNumber": "1234 4321 4567 7654",
        "expiringDate": "02/1922",
        "cardFlag": "Visa"
    },
    "date": "22/02/1993",
    "status": "Aprovado",
    "total": 523.3
}'

## DELETE
curl -X DELETE \
  http://localhost:9090/orders/2 \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: cb830ac9-7233-966a-928a-5cae8232f15a'

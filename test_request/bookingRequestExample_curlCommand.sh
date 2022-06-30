curl -X POST -H 'Content-Type: application/json' -i http://localhost:8082/bookFlightTicket --data '{
"passengerInfo":
  {
   "name": "Emanuele",
   "email": "prova@gmail.com",
   "source": "Rome",
   "destination": "Prague",
    "travelDate": "13-02-2022",
    "pickupTime": "13:23",
    "arrivalTime": "14:30",
    "fare": "20000"
  },
"paymentInfo":
  {
    "accountNo": "acc1",
    "cardType": "DEBIT"
  }
}'

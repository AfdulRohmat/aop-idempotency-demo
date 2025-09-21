docker exec -i kafka kafka-console-producer \
--broker-list localhost:9092 \
--topic transactions <<EOF
{"transactionId":"TX-4001","accountNumber":"1234567890","amount":15000}
EOF

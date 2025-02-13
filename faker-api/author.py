import requests
import json
from faker import Faker

fake = Faker()

url = "http://localhost:8080/api/authors"

num_requests = int(input("Nhập số lượng request bạn muốn fake: "))

headers = {
  'Content-Type': 'application/json'
}

# Fake và gửi các request
for _ in range(num_requests):
    payload = json.dumps({
      "name": fake.name(),
      "nationality": fake.country()
    })

    response = requests.request("POST", url, headers=headers, data=payload)

    print(f"Response for request {_+1}: {response.text}")

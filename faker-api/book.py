import requests
import json
from faker import Faker

fake = Faker()

url = "http://localhost:8080/api/books"

num_requests = int(input("Nhập số lượng request bạn muốn fake: "))

headers = {
  'Content-Type': 'application/json'
}

for _ in range(num_requests):
    payload = json.dumps({
        "title": fake.sentence(nb_words=3),
        "authorId": fake.random_int(min=1, max=100),
        "publishedDate": fake.date_this_decade().strftime("%Y-%m-%d"),
        "isbn": fake.isbn13(),
        "price": round(fake.random_number(digits=2) + fake.random.random(), 2)
    })

    response = requests.request("POST", url, headers=headers, data=payload)

    print(f"Response for request {_+1}: {response.text}")

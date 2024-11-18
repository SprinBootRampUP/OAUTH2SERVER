```toml
name = 'create course'
description = '/api/course/create'
method = 'POST'
url = 'http://localhost:3008/api/course/create'
sortWeight = 6000000
id = 'a2b660cc-42eb-4bfb-aa6c-b23130a3f3e7'

[[headers]]
key = 'Authorization'
value = 'Bearer eyJraWQiOiJmYTgxZGQ3Yi1jMjViLTQxZjItODczYi04NjNjYzNkNzI3MDgiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJBVVRIT1IiLCJhdWQiOiJyZXNvdXJjZV9zZXJ2ZXIiLCJuYmYiOjE3MzEwNDU1ODcsInNjb3BlIjpbIm9wZW5pZCJdLCJyb2xlcyI6WyJBVVRIT1IiLCJVU0VSIl0sImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6MzAwNiIsImV4cCI6MTczMTA0NTg4NywiaWF0IjoxNzMxMDQ1NTg3LCJ1c2VySWQiOjQsImp0aSI6IjdiNGQwMGYyLWE4MDEtNDgxMS04MjAxLTY2NmFkYjZiNTI4MiJ9.EKM3Bu7kAES4YCa3u8a-J0OLuQ6UCDIE26c7MHxi0oRE5a4i0ZfCKiXlLgfZBQeT-hsavwH1Ur1UEeZqiosJs-K3M7hKTji8qJet9Qhnofv8M8nLOoZ9isCbMiI2vL7ghRitWkO2CbaxIX966qgIRjm5KYTqDvs1LEG3wgm4YhkscA3BK0WzH7Zb_cIf9wI85vPWhLmFXwjPtb1BhaenxK6EHIGTtroF8cSf7cGysDkhx9HgYEEhAngqH9dsTCvIDHUabrHQftZS-J6E9rRV0CUxS-1fdGIFiGgTn4p1wC15N4e7loA14oqNB0ilIn5my--KXkhauLPY7x-RTN03sw'
disabled = true

[auth.bearer]
token = 'eyJraWQiOiI3NDczZmNjZi1jMjVkLTQ3ZjktOWE0Yy00OGRiMjY4ZDhlMWEiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJBVVRIT1IiLCJhdWQiOiJyZXNvdXJjZV9zZXJ2ZXIiLCJuYmYiOjE3MzE5MTQyMDEsInNjb3BlIjpbIm9wZW5pZCJdLCJyb2xlcyI6WyJBVVRIT1IiLCJVU0VSIl0sImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6MzAwNiIsImV4cCI6MTczMjAwMDYwMSwiaWF0IjoxNzMxOTE0MjAxLCJ1c2VySWQiOjQsImp0aSI6IjVmMzIyNjk0LTg2M2EtNDI5Yi04MzE1LTU1MWVjMjY4NjlmZCJ9.pcfHkilP8x9NPKnos88sZBZStjUblyVQLFzycp9t2TgtBn3oN4kM-oI26RhcRS-oq4fVlHc0N8-AK_JP2EDn6HwNGRjzTNmy9bOrhJi1AOvOi-lMLyU0PSV5gqsmuUUiU1cFaB-hwTHDv3_cxJqsN-_NK4PuetAFTBErpPIq-Uh6c4NoxlYxygfwoQAokkgU0FYSm9mI0RCsiQue-tZ5F64JcbSxdBo0UocisNM4Yu1HO941Q2YoyCl10KAFxB21bq77usrkgFR_kfgaYmELDtA3VLQsdxG0g_lhy2h5_EtB0e6WsdDLwsAT2wwCE4jCZ4Uw9R9NRRDH4lsXO6jbkQ'

[body]
type = 'JSON'
raw = '''
{
  "courseTitle": "refcb",
  "courseLevel": "BEGINNER",
  "price": "string",
  "sections": [
    {
      "name": "string",
      "orderNumber": "string",
      "lectures": [
        {
          "name": "string",
          "resource": {
            "name": "string",
            "size": 0,
            "url": "string"
          }
        }
      ]
    }
  ]
}'''
```

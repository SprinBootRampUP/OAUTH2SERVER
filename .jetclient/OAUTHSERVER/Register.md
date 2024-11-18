```toml
name = 'Register'
description = '/oauth/api/register'
method = 'POST'
url = 'http://localhost:3006/oauth/api/register'
sortWeight = 4000000
id = 'd6fcf2ae-ee76-47ef-857a-578a88563604'

[body]
type = 'JSON'
raw = '''
{
    "userName" :"sharan",
    "password" : "sharan",
"phoneNumber" :"89875437",
"address" :"chennai",
    "email" : "mail@gmail.com"

}'''
```

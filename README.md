# Task

## Web Service

Implement RESTful Web Service for manipulating Teachers. Use in-memory storage.

Teacher has:
* Id
* Name
* Birthday
* Lessons

Lesson has:
* Id
* Name
* Duration

Implement functionality for CRUD operations for Teachers’ service.

Web Service should consume objects in JSON format and return objects in XML format.
Implement method for getting the busiest Teacher.
In case of Teacher was not changed during the update – 304 status code should be set to response.

Exceptions’ catching should be realized using ExceptionMapper.

## Client 

Implement HTTP client for the Web Service above.
Demonstrate work of the Web Service using the Client.

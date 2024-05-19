## Technology Stack:
1. Programming language - `Java`
2. Build and project management tool - `Maven`
3. Testing framework - `TestNG`
4. Automation framework - `Selenium WebDriver`

## Tasks:
Create a new automation project for API testing.
Use the following dummy pages for tests:
- https://restcountries.com/ 
- https://dummy.restapiexample.com/

## CountriesAPI
## Test Plan:
### Test case nr 1: Get one country all details.
1. Get Romania country details.
   Expected result: Romania country details are returned.

### Test case nr 2: Use GET request for all countries.
1. Check if the GET request for all the countries is with succes.
   Expected result: The welcome message appears after the valid login.

### Test case nr 3: Use a currency to get a country as a result.
1. Get the Hungary country searching after huf currency.
   Expected result: Hungary country is returned.

### Test case nr 4: Perform a negative test, by using an invalid caountry name.
1. Run a GET call and use "Bucharest345" as capital of Romania.
2. Check the response.
   Expected result: The test failed, the capital of Romania is "Bucharest", not "Bucharest345".

### Test case nr 5: Verify the alternative spelling for a country.
1. Verify the alternative spelling of Romania.
   Expected result: Alternative spelling of Romania contains "România" also.

### Test case nr 6: Check the bourders for a country.
1. Verify if the country borders for Romania are MDA, BGR, HUN, SRB and UKR.
   Expected result: Test pasts with succes.

### Test case nr 7: Verifies if the official language in Romania is Romanian."
1. Verifies if the official language in Romania is Romanian."
   Expected result: Test passed with succes.

### Test case nr 8: Return the data and the response time for a GET country request. 
1. Run a GET request and print the data with the response time.
   Expected result: Test passed with succes.

------------------------------------------------------------------------------------------------------------------------
## EmployeesApi
## Test Plan:
### Tests case nr 1: Print all employees.
Expected result: All employees are printed.

### Test case nr 2: Print an employee with ids help.
Expected result: The employee with id 1 is printed.

### Test case nr 3: Create a new employee.
Expected result: New employee is created.

### Test case nr 4: Update an existent employee.
Expected result: An existent empoyee is updated,

### Test case nr 5: Delete an Employee.
Expected result: An existent empoyee is deleted,

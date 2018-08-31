# TreeTop Commons Organization Service

This project contains a RESTful service implemented in Java that returns a filtered set of organizations from a static list.

### TODOs

The following are some things I would expand upon or implement differently given more time, in no particular order:
* Needs a lot more error/exception handling.
* The organization data file is being parsed and read into memory the first time the code attempts to access the data. This functionality could be moved to an application initialization stage. Normally, this information would be kept in a data store of some kind.
* Add unit tests.
* Some of the organization names in the CSV file contain commas and so are enclosed in quotes. These are currently not being handled correctly by the parser. To correct this, I'd identify organizations that meet this criteria and parse those records character-by-character.
* More method and inline comments.
* The name and location of the CSV file in the jar is hard-coded. It could be passed to the application as a parameter, or set as an application configuration property or environment variable.
* More logging.
* Also see miscellaneous TODOs in the code.

### Instructions for running

#### Prerequisites
* Maven is installed (i.e., `mvn` can be run from the command line)
* Java 8 is installed.

#### Building and running the application

1. Clone the project from GitHub: []()
2. From the root directory of the project, run: `mvn clean package`. This will create a jar file in the directory `target` called `TreeTopCommonsService.jar`.
3. Run the command `java -jar TreeTopCommonsService.jar`. The service is now running on port 8080. You should see some startup messages logged to the terminal window.
4. Using `curl` or a REST client of you choice, issue requests against the url `http://localhost:8080/treetop/organizations`; e.g.:

`http://localhost:8080/treetop/organizations?category=non-profit&orderBy=state&direction=DSC`

#### Notes
* Fields to filter on (e.g., `category` in the example above) are case-sensitive, as are the `orderBy` and `direction` parameters.
* Parameter values are case-insensitive; e.g., specifying `DSC` or `dsc` for the `direction` parameter are equivalent.
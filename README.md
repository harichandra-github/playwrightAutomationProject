# Playwright Automation Project

A comprehensive web automation testing framework built with Playwright for Java, TestNG, and Extent Reports. This project demonstrates automated testing of the Sauce Demo application with modern testing practices.

##  Features

- **Playwright for Java**: Modern web automation with excellent browser support
- **TestNG Framework**: Advanced testing framework with parallel execution
- **Extent Reports**: Beautiful HTML test reports with detailed analytics
- **Log4j2 Logging**: Comprehensive logging for debugging and monitoring
- **Page Object Model**: Maintainable and scalable test architecture
- **Retry Mechanism**: Automatic retry for failed test cases
- **Multi-browser Support**: Chrome, Firefox, and Safari support
- **Parallel Execution**: Configurable thread execution for faster test runs

##  Prerequisites

- Java 21 or higher
- Maven 3.6 or higher
- Git

##  Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/harichandra-github/playwrightAutomationProject.git
   cd playwrightAutomationProject
   ```

2. **Install Playwright browsers**
   ```bash
   mvn exec:java -e -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install"
   ```

3. **Verify installation**
   ```bash
   mvn clean compile
   ```

##  Project Structure

```
playwrightAutomationProject/
├── src/
│   ├── main/java/com/qa/saucedemo/
│   │   ├── constants/
│   │   │   └── AppConstants.java          # Application constants
│   │   ├── factory/
│   │   │   └── PlaywrightFactory.java     # Browser factory
│   │   ├── listeners/
│   │   │   ├── ExtentManager.java         # Extent reports manager
│   │   │   ├── ExtentTestManager.java     # Test management
│   │   │   ├── RetryFailed.java           # Retry logic
│   │   │   ├── RetryListener.java         # Retry listener
│   │   │   └── TestListener.java          # Test lifecycle events
│   │   ├── logger/
│   │   │   └── Log.java                   # Logging utilities
│   │   ├── pages/
│   │   │   ├── CartPage.java              # Cart page object
│   │   │   ├── HomePage.java              # Home page object
│   │   │   ├── LoginPage.java             # Login page object
│   │   │   └── ProductPage.java           # Product page object
│   │   └── utilities/
│   │       └── CommonFunctions.java       # Common utility functions
│   └── test/
│       ├── java/com/qa/saucedemo/
│       │   ├── base/
│       │   │   └── BaseTest.java          # Base test class
│       │   └── tests/
│       │       └── LoginTest.java         # Login test scenarios
│       └── resources/
│           ├── config/
│           │   └── config.properties      # Configuration properties
│           ├── log4j2.xml                 # Logging configuration
│           └── runners/
│               └── testng.xml             # TestNG suite configuration
├── Reports/                               # Generated test reports
├── logs/                                  # Application logs
└── pom.xml                               # Maven dependencies
```

##  Configuration

### Browser Configuration
Edit `src/test/resources/config/config.properties`:

```properties
# Browser settings
browser=firefox                    # chrome, firefox, safari
headless=false                     # true for headless mode

# Application settings
url=https://www.saucedemo.com/
username=standard_user
password=secret_sauce

# Test settings
retryCount=3                       # Number of retry attempts for failed tests
```

### TestNG Configuration
The test suite is configured in `src/test/resources/runners/testng.xml`:
- Parallel execution with 5 threads
- Extent reports and retry listeners enabled
- Test classes included in the suite

##  Running Tests

### Run all tests
```bash
mvn clean test
```

### Run specific test class
```bash
mvn test -Dtest=LoginTest
```

### Run specific test method
```bash
mvn test -Dtest=LoginTest#loginToApp
```

### Run with TestNG XML
```bash
mvn test -DsuiteXmlFile=src/test/resources/runners/testng.xml
```

### Run in parallel
```bash
mvn test -DthreadCount=3
```

##  Test Reports

### Extent Reports
After test execution, detailed HTML reports are generated in the `Reports/TestReport/` directory. Each report includes:
- Test execution summary
- Pass/fail statistics
- Screenshots for failed tests
- Detailed test logs
- System information

### Logs
Application logs are stored in `logs/app.log` with different log levels:
- INFO: General information
- ERROR: Error messages
- DEBUG: Debug information
- WARN: Warning messages

##  Key Components

### Page Object Model
The project follows the Page Object Model pattern:
- **LoginPage**: Handles login functionality
- **HomePage**: Manages home page interactions
- **CartPage**: Cart-related operations
- **ProductPage**: Product page interactions

### Playwright Factory
`PlaywrightFactory` manages browser initialization and configuration:
- Multi-browser support
- Configurable browser options
- Context and page management

### Listeners
- **ExtentManager**: Generates comprehensive test reports
- **RetryListener**: Automatically retries failed tests
- **TestListener**: Handles test lifecycle events

### Common Functions
Utility functions for:
- Timestamp generation
- Configuration loading
- Common operations

##  Test Scenarios

### Login Tests
- Valid login with standard user
- Navigation to home page
- Page title and URL verification

### Future Test Scenarios
- Product sorting and filtering
- Add/remove items from cart
- Checkout process
- User menu interactions

##  Best Practices

1. **Page Object Model**: Maintains separation between test logic and page interactions
2. **Configuration Management**: Externalized configuration for different environments
3. **Logging**: Comprehensive logging for debugging and monitoring
4. **Retry Mechanism**: Improves test stability with automatic retries
5. **Parallel Execution**: Faster test execution with configurable threads
6. **Extent Reports**: Professional test reporting with detailed analytics

##  Troubleshooting

### Common Issues

1. **Browser not found**
   ```bash
   mvn exec:java -e -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install"
   ```

2. **Java version issues**
   - Ensure Java 21+ is installed
   - Check JAVA_HOME environment variable

3. **Maven dependencies**
   ```bash
   mvn clean install
   ```

### Debug Mode
Enable debug logging by modifying `log4j2.xml`:
```xml
<Logger name="com.qa.saucedemo" level="DEBUG"/>
```




##  Author

**Harichandra Chaudhari**
- GitHub: [@harichandra-github](https://github.com/harichandra-github)
- Linkedin: [@harichandra-chaudhari](https://www.linkedin.com/in/harichandra-chaudhari/)
- Email: [harichandrachaudhari21@gmail.com](mailto:harichandrachaudhari21@gmail.com)
- Phone: +91-866-900-9231

##  Acknowledgments

- [Playwright](https://playwright.dev/) - Modern web automation
- [TestNG](https://testng.org/) - Testing framework
- [Extent Reports](https://extentreports.com/) - Test reporting
- [Sauce Demo](https://www.saucedemo.com/) - Test application

---

**Happy Testing! **

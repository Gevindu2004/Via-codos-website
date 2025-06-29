# Viacodos Website with Admin System

A Spring Boot web application with a complete admin system for managing projects and content.

## Features

### Public Features
- **Home Page**: Landing page with company information
- **About Us**: Company information and team details
- **Projects**: Showcase of completed projects (dynamically managed by admins)
- **Contact**: Contact information and form
- **Dashboard**: Public dashboard view

### Admin Features
- **Secure Login**: Admin authentication system
- **Admin Dashboard**: Overview of projects and statistics
- **Project Management**: Add, edit, and delete projects
- **Content Management**: Manage website content through admin panel
- **Role-based Access**: Only admins can access admin features

## Technology Stack

- **Backend**: Spring Boot 3.5.3, Java 24
- **Database**: H2 (in-memory for development)
- **Security**: Spring Security with role-based authentication
- **Frontend**: Thymeleaf templates, CSS, JavaScript
- **Build Tool**: Gradle
- **Containerization**: Docker

## Quick Start

### Prerequisites
- Java 17 or higher
- Docker (optional)

### Local Development

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd viacodos2-website
   ```

2. **Run the application**
   ```bash
   ./gradlew bootRun
   ```

3. **Access the application**
   - Website: http://localhost:8080
   - Admin Login: http://localhost:8080/login
   - H2 Database Console: http://localhost:8080/h2-console

### Docker Deployment

1. **Build the Docker image**
   ```bash
   docker build -t viacodos-website .
   ```

2. **Run the container**
   ```bash
   docker run -p 8080:8080 viacodos-website
   ```

3. **Access the application**
   - Website: http://localhost:8080
   - Admin Login: http://localhost:8080/login

## Admin System

### Default Admin Credentials
- **Username**: admin
- **Password**: admin123
- **Email**: admin@viacodos.com

### Admin Features

#### 1. Login
- Navigate to `/login`
- Enter admin credentials
- Redirected to admin dashboard after successful login

#### 2. Admin Dashboard (`/admin/dashboard`)
- Overview of total projects
- Quick actions to add new projects
- Recent projects list
- Navigation to other admin features

#### 3. Project Management (`/admin/projects`)
- View all projects in a table format
- Add new projects
- Edit existing projects
- Delete projects
- Project details include:
  - Title
  - Description
  - Image URL
  - Technologies used
  - GitHub URL
  - Live demo URL

#### 4. Project Form (`/admin/projects/new` or `/admin/projects/{id}/edit`)
- Form to create or edit projects
- All project fields with validation
- Image URL support
- Technology tags
- External links (GitHub, Live Demo)

### Security Features

- **Role-based Access Control**: Only users with ADMIN role can access admin features
- **Password Encryption**: Passwords are encrypted using BCrypt
- **Session Management**: Secure session handling
- **CSRF Protection**: Enabled for all forms
- **Input Validation**: Server-side validation for all inputs

## Database Schema

### Users Table
- `id`: Primary key
- `username`: Unique username
- `password`: Encrypted password
- `email`: Unique email address
- `roles`: Many-to-many relationship with roles

### Roles Table
- `id`: Primary key
- `name`: Role name (ROLE_USER, ROLE_ADMIN)

### Projects Table
- `id`: Primary key
- `title`: Project title
- `description`: Project description
- `image_url`: URL to project image
- `technologies`: Technologies used
- `github_url`: GitHub repository URL
- `live_url`: Live demo URL
- `created_at`: Creation timestamp
- `updated_at`: Last update timestamp

## Configuration

### Application Properties
Key configuration options in `application.properties`:

```properties
# Database Configuration
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=sa
spring.datasource.password=password

# Security Configuration
spring.security.user.name=admin
spring.security.user.password=admin123

# Server Configuration
server.port=8080
```

### Customization

#### Adding New Admin Users
1. Access H2 console at `/h2-console`
2. Use credentials: sa/password
3. Insert new user with encrypted password
4. Assign ADMIN role

#### Changing Default Admin Password
1. Update `application.properties`
2. Or use the H2 console to update the password hash

## File Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/viacodos/viacodos2/
│   │       ├── config/
│   │       │   ├── SecurityConfig.java
│   │       │   └── DataInitializer.java
│   │       ├── controller/
│   │       │   ├── HomeController.java
│   │       │   ├── AdminController.java
│   │       │   └── AuthController.java
│   │       ├── entity/
│   │       │   ├── User.java
│   │       │   ├── Role.java
│   │       │   ├── ERole.java
│   │       │   └── Project.java
│   │       ├── repository/
│   │       │   ├── UserRepository.java
│   │       │   ├── RoleRepository.java
│   │       │   └── ProjectRepository.java
│   │       ├── service/
│   │       │   ├── UserService.java
│   │       │   └── ProjectService.java
│   │       └── Viacodos2Application.java
│   └── resources/
│       ├── static/
│       │   ├── css/
│       │   │   ├── style.css
│       │   │   └── dashboard.css
│       │   ├── js/
│       │   │   └── script.js
│       │   └── images/
│       ├── templates/
│       │   ├── admin/
│       │   │   ├── dashboard.html
│       │   │   ├── projects.html
│       │   │   └── project-form.html
│       │   ├── login.html
│       │   ├── index.html
│       │   ├── Projects.html
│       │   └── ...
│       └── application.properties
```

## Deployment

### Production Considerations

1. **Database**: Replace H2 with a production database (PostgreSQL, MySQL)
2. **Security**: Change default admin credentials
3. **HTTPS**: Enable SSL/TLS
4. **Environment Variables**: Use environment variables for sensitive data
5. **Logging**: Configure proper logging
6. **Monitoring**: Add health checks and monitoring

### Environment Variables
```bash
# Database
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/viacodos
SPRING_DATASOURCE_USERNAME=your_username
SPRING_DATASOURCE_PASSWORD=your_password

# Security
ADMIN_USERNAME=your_admin_username
ADMIN_PASSWORD=your_admin_password
ADMIN_EMAIL=your_admin_email

# Server
SERVER_PORT=8080
```

## Troubleshooting

### Common Issues

1. **Port already in use**
   - Change `server.port` in `application.properties`
   - Or kill the process using the port

2. **Database connection issues**
   - Check database credentials
   - Ensure database is running
   - Verify connection URL

3. **Admin login not working**
   - Check default credentials
   - Verify user exists in database
   - Check role assignments

4. **Docker build fails**
   - Ensure Docker is running
   - Check available disk space
   - Verify Dockerfile syntax

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Test thoroughly
5. Submit a pull request

## License

This project is licensed under the MIT License.

## Support

For support and questions:
- Create an issue in the repository
- Contact: admin@viacodos.com 
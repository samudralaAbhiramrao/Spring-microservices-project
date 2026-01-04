Run - task start_infra (Postgres UI , RabbitMQ, Catalog-db, Order-db)
Run - task stop_infra (Stop all infra services)
Note :

- If you are creating a new Module , make sure you run "mvn -N wrapper:wrapper" Command

Once the start_infra is done, you normally run the application

task start - will start both infra and application(Docker Conatiner)

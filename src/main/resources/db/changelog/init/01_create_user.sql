DO
$$
    BEGIN
        CREATE ROLE aimasters_app;
        CREATE ROLE aimaster_owner;
    EXCEPTION
        WHEN duplicate_object THEN
        -- роль уже создана
    END
$$;

DO
$$
    BEGIN
        CREATE USER master
            LOGIN
            PASSWORD 'bombaster'
            IN ROLE aimaster_owner;
    EXCEPTION
        WHEN duplicate_object THEN
        -- пользователь уже создана
    END
$$;
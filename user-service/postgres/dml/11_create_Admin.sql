\c root

INSERT INTO public.users(uuid, dt_create, dt_update, fio, mail, password, role, status)
	VALUES (gen_random_uuid (), now(), now(), 'Admin', 'admin@admin.ru', '$2a$10$u6ijZLGXsy31Q957lJxS8elMVHh9mSHcJhx9OI.Om82bJJgh/UO3G', 0, 1);

import { error, redirect } from "@sveltejs/kit";
import type { PageServerLoad, Actions } from "./$types";
import type { User } from "$lib/models/User";

export const load = (async ({ fetch, params }) => {

    if (!/^\d+$/.test(params.userId)) throw error(404, "Not Found");

    // json-server
    // const res = await fetch(`http://localhost:3000/users/${params.userId}`);

    // Quarkus
    const res = await fetch(`http://localhost:8080/users/${params.userId}`);

    if (!res.ok) throw error(res.status, "Not Found");

    const user: User = await res.json();

    return { user };

}) satisfies PageServerLoad;

export const actions = {

    create: async ({ fetch, request }) => {
        const { name } = Object.fromEntries(await request.formData());

        // json-server
        // const res = await fetch(`http://localhost:3000/users`, {
        //     method: "POST",
        //     headers: { "Content-Type": "application/json" },
        //     body: JSON.stringify({ name })
        // });

        // Quarkus
        const res = await fetch(`http://localhost:8080/users`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ name })
        });

        if (!res.ok) throw error(res.status, "Error");

        redirect(303, "/");
    },

    update: async ({ fetch, request }) => {
        const { id, name } = Object.fromEntries(await request.formData());

        // json-server
        // const res = await fetch(`http://localhost:3000/users/${id}`, {
        //     method: "PUT",
        //     headers: { "Content-Type": "application/json" },
        //     body: JSON.stringify({ name })
        // });

        // Quarkus
        const res = await fetch(`http://localhost:8080/users/${id}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ id, name })
        });

        if (!res.ok) throw error(res.status, "Error");

        redirect(303, '/');
    },

    delete: async ({ fetch, request }) => {
        const { id } = Object.fromEntries(await request.formData());

        // json-server
        // const res = await fetch(`http://localhost:3000/users/${id}`, {
        //     method: "DELETE"
        // });

        // Quarkus
        const res = await fetch(`http://localhost:8080/users/${id}`, {
            method: "DELETE"
        });

        if (!res.ok) throw error(res.status, "Error");

        redirect(303, "/");
    }

} satisfies Actions;

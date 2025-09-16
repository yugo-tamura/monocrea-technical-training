import { error } from "@sveltejs/kit";
import type { PageServerLoad } from "./$types";
import type { User } from "$lib/models/User";

export const load = (async ({ fetch, url }) => {

	let keywordQuery: { key: keyof User, val: string } = { key: "id", val: "" };
	const sortQuery: { key: "sort", val: string } = { key: "sort", val: "" };
	let page = "1";
	for (const [key, val] of url.searchParams.entries()) {
		switch (key) {
			case "id":
			case "name":
				keywordQuery = { key, val };
				break;
			case "sort":
				sortQuery.val = val;
				break;
			case "page":
				page = val;
				break;
		}
	}
	const sortVal = sortUsers(sortQuery.val);

	// json-server
	// const query = new URLSearchParams();
	// if (keywordQuery.val) {
	// 	if (keywordQuery.key === "id") {
	// 		query.set("id", keywordQuery.val);
	// 	} else if (keywordQuery.key === "name") {
	// 		query.set("name_like", keywordQuery.val);
	// 	}
	// }
	// query.set("_sort", sortVal.sort);
	// query.set("_order", sortVal.order);
	// const res = await fetch(`http://localhost:3000/users?${query.toString()}`);

	// Quarkus
	const query = new URLSearchParams({
		condition: keywordQuery.key,
		keyword: keywordQuery.val,
		sort: sortVal.sort,
		order: sortVal.order,
	}).toString();
	const res = await fetch(`http://localhost:8080/users?${query}`);

	if (!res.ok) throw error(404, "Not Found");
	let users: User[] = await res.json();

	// ユーザー情報の取得件数
	const total = users.length;

	// 1ページの表示件数上限
	const limit = 5;

	// 現在のページ
	const current = Number(page);

	// 最後のページ
	const max = Math.max(Math.ceil(total / limit), 1);

	// 不正パラメータ
	const invalid = !Number.isInteger(current) || current <= 0 || current > max || !["", "1", "2", "3", "4"].includes(sortQuery.val);
	if (invalid) throw error(404, "Not Found");

	// ページの最初のユーザーのインデックス
	const start = ((current - 1) * limit);

	// ページの最後のユーザーのインデックス
	const end = (current === max ? total : start + limit);

	// ページに表示するユーザーの配列
	users = users.slice(start, end);

	return {
		users,
		total,
		limit,
		current,
		max,
		start,
		end,
		keywordQuery,
		sortQuery,
	};

}) satisfies PageServerLoad;;

function sortUsers(sort: string): { sort: keyof User; order: "asc" | "desc" } {
	switch (sort) {
		case "1":
			return { sort: "id", order: "asc" };
		case "2":
			return { sort: "id", order: "desc" };
		case "3":
			return { sort: "name", order: "asc" };
		case "4":
			return { sort: "name", order: "desc" };
		default:
			return { sort: "id", order: "asc" };
	}
};

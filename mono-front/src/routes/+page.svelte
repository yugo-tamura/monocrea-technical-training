<script lang="ts">
	import Pagination from '$lib/components/Pagination.svelte';
	import SearchForm from '$lib/components/SearchForm.svelte';
	import Sort from '$lib/components/Sort.svelte';
	import type { PageProps } from './$types';

	let { data }: PageProps = $props();
</script>

<svelte:head>
	<title>ユーザー検索画面</title>
</svelte:head>

<div class="search-page">
	<div class="search-content">
		<Sort keywordQuery={data.keywordQuery} sortQuery={data.sortQuery} />

		<SearchForm keywordQuery={data.keywordQuery} sortQuery={data.sortQuery} />

		<p class="result-heading">
			検索結果（{data.total} 件）
		</p>

		<div class="user-list">
			{#each data.users ?? [] as user}
				<p class="user-item">
					<a href={`/detail/${user.id}`}>{user.id}：{user.name}</a>
				</p>
			{/each}
		</div>
	</div>

	<Pagination
		total={data.total}
		limit={data.limit}
		max={data.max}
		current={data.current}
		start={data.start}
		end={data.end}
		keywordQuery={data.keywordQuery}
		sortQuery={data.sortQuery}
	/>
</div>

<style>
	.search-page {
		display: flex;
		flex-direction: column;
		flex: 1;
	}

	.search-content {
		flex: 1;
	}

	.result-heading {
		font-weight: bold;
		font-size: 1.1rem;
		padding-bottom: 0.3rem;
		border-bottom: 2px solid #ccc;
		margin: 1.5rem 0 1rem 0;
	}

	.user-list {
		display: flex;
		flex-direction: column;
		gap: 1.5rem;
	}

	.user-item {
		margin: 0;
		overflow-wrap: anywhere;
	}

	.user-item a {
		color: #007acc;
		text-decoration: none;
	}

	.user-item a:hover {
		text-decoration: underline;
	}
</style>

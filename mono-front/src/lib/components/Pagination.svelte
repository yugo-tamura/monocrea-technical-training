<script lang="ts">
	type Props = {
		total?: number;
		limit?: number;
		current?: number;
		max?: number;
		start?: number;
		end?: number;
		keywordQuery?: { key: string; val: string };
		sortQuery?: { key: string; val: string };
	};

	let {
		total = 0,
		limit = 0,
		current = 1,
		max = 1,
		start = 0,
		end = 0,
		keywordQuery,
		sortQuery
	}: Props = $props();

	let query = $state('');

	$effect(() => {
		const params = new URLSearchParams();
		if (keywordQuery?.val) params.append(keywordQuery.key, keywordQuery.val);
		if (sortQuery?.val) params.append(sortQuery.key, sortQuery.val);
		query = params.toString() + '&';
	});
</script>

{#if total && total > limit}
	<div class="pagination">
		{#if current > 1}
			<a href={`?${query}page=${current - 1}`} class="button"> PREV </a>
		{/if}
		<p>{start + 1} - {end} of {total}</p>
		{#if current < max}
			<a href={`?${query}page=${current + 1}`} class="button"> NEXT </a>
		{/if}
	</div>
{/if}

<style>
	.pagination {
		display: flex;
		align-items: center;
		justify-content: center;
		gap: 1rem;
		margin: 1rem auto;
	}

	.pagination p {
		margin: 0;
		color: #333;
	}

	.button {
		display: inline-flex;
		align-items: center;
		justify-content: center;
		padding: 0.4rem 1rem;
		border-radius: 6px;
		border: 1px solid #ccc;
		background: #f8f8f8;
		color: #333;
		text-decoration: none;
		transition:
			background 0.2s,
			color 0.2s,
			border-color 0.2s;
	}

	.button:hover {
		background: #000;
		color: #fff;
	}
</style>

<script lang="ts">
	import { goto } from '$app/navigation';

	type Props = {
		keywordQuery?: { key: string; val: string };
		sortQuery?: { key: string; val: string };
	};

	let { keywordQuery, sortQuery }: Props = $props();

	const searchConditions = [
		{
			key: 'id',
			text: 'IDで検索'
		},
		{
			key: 'name',
			text: '名前で検索'
		}
	];

	let selected = $state(keywordQuery?.val ? keywordQuery.key : searchConditions[0].key);
	let keyword = $state(keywordQuery?.val);

	const onsubmit = (event: Event) => {
		event.preventDefault();
		const params = new URLSearchParams();
		if (keyword) params.append(selected, keyword);
		if (sortQuery?.val) params.append(sortQuery.key, sortQuery.val);
		goto(`?${params.toString()}`);
	};
</script>

<form {onsubmit}>
	<label class="select-label">
		検索条件：
		<select bind:value={selected}>
			{#each searchConditions as condition}
				<option value={condition.key}>
					{condition.text}
				</option>
			{/each}
		</select>
	</label>
	<input type="text" name="search" bind:value={keyword} placeholder="Search..." />
	<button type="submit" aria-label="Search">
		<svg class="icon" stroke-width="2" viewBox="0 0 24 24" fill="none">
			<path stroke="white" d="M10 10m-7 0a7 7 0 1 0 14 0a7 7 0 1 0 -14 0" />
			<path stroke="white" d="M21 21l-6 -6" />
		</svg>
	</button>
</form>

<style>
	form {
		display: flex;
		align-items: center;
		justify-content: center;
		gap: 0.5rem;
		border: 1px solid #ccc;
		border-radius: 8px;
		padding: 0.5rem;
		margin: 1rem auto;
		background: #fafafa;
		box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
	}

	button {
		display: flex;
		align-items: center;
		justify-content: center;
		width: 40px;
		height: 40px;
		background-color: #000;
		border: none;
		border-radius: 6px;
		cursor: pointer;
		transition: background 0.2s;
	}

	button:hover {
		background-color: #333;
	}

	.icon {
		width: 34px;
		height: 34px;
	}

	@media (max-width: 768px) {
		form {
			flex-direction: column;
			align-items: stretch;
			gap: 0.75rem;
			padding: 1rem;
		}

		select,
		input,
		button {
			width: 100%;
			height: 42px;
			box-sizing: border-box;
		}
	}
</style>

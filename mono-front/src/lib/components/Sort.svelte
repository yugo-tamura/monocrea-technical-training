<script lang="ts">
	import { goto } from '$app/navigation';

	type Props = {
		keywordQuery?: { key: string; val: string };
		sortQuery?: { key: string; val: string };
	};

	let { keywordQuery, sortQuery }: Props = $props();

	const sortList = [
		{
			id: '1',
			text: 'ID昇順'
		},
		{
			id: '2',
			text: 'ID降順'
		},
		{
			id: '3',
			text: '名前昇順'
		},
		{
			id: '4',
			text: '名前降順'
		}
	];

	let selected = $state(sortQuery?.val || sortList[0].id);

	const onchange = (event: Event) => {
		const sort = (event.target as HTMLSelectElement).value;
		const params = new URLSearchParams();
		if (keywordQuery?.val) params.append(keywordQuery.key, keywordQuery.val);
		params.append(sortQuery!.key, sort);
		goto(`?${params.toString()}`);
	};
</script>

<div class="sort-container">
	<label class="select-label">
		ソート順：
		<select bind:value={selected} {onchange}>
			{#each sortList as sort}
				<option value={sort.id}>
					{sort.text}
				</option>
			{/each}
		</select>
	</label>
</div>

<style>
	.sort-container {
		margin: 0 auto 1.5rem auto;
	}
</style>

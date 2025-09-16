<script>
	import { page } from '$app/state';
	import { goto } from '$app/navigation';
</script>

<svelte:head>
	<title>error</title>
</svelte:head>

<div class="contain">
	<div class="error-message">
		{page.error?.message}
	</div>
	<button class="buttonEffect" type="button" onclick={() => goto('/')}>検索画面に戻る</button>
</div>

<style>
	.error-message {
		background: #fdecea;
		color: #e74c3c;
		border: 1px solid #e74c3c;
		padding: 1rem;
		border-radius: 6px;
		font-weight: bold;
		text-align: center;
		margin-top: 3rem;
		margin-bottom: 1.5rem;
	}
</style>

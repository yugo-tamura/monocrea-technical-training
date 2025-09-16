<script lang="ts">
	import type { PageProps } from './$types';
	import { goto } from '$app/navigation';

	let { data }: PageProps = $props();
</script>

<svelte:head>
	<title>ユーザー詳細画面</title>
</svelte:head>

<div class="detail-wrapper">
	<div class="detail-card">
		<p class="note">※新規登録の際はIDが連番で登録されます</p>
		<form method="post">
			<input type="hidden" name="id" value={data.user.id} />
			<label class="form-label">
				{data.user.id}：
				<input type="text" name="name" value={data.user.name} placeholder="UserName..." />
			</label>
			<div class="button-group">
				<button class="buttonEffect" type="button" onclick={() => goto('/')}>戻る</button>
				<div class="right-buttons">
					<button formaction="?/create" class="buttonEffect">新規登録</button>
					<button formaction="?/update" class="buttonEffect">更新</button>
					<button formaction="?/delete" class="buttonEffect">削除</button>
				</div>
			</div>
		</form>
	</div>
</div>

<style>
	.detail-wrapper {
		display: flex;
		padding: 2rem 0;
	}

	.detail-card {
		width: 100%;
		border: 1px solid #ddd;
		border-radius: 8px;
		padding: 2rem;
		box-shadow: 0 4px 10px rgba(0, 0, 0, 0.05);
	}

	form {
		display: flex;
		flex-direction: column;
		gap: 1.5rem;
	}

	.note {
		font-size: 0.9rem;
		color: #333;
		margin-bottom: 1rem;
	}

	.form-label {
		display: flex;
		align-items: center;
		gap: 0.5rem;
		font-weight: bold;
	}

	input[type='text'] {
		padding: 0.6rem 0.8rem;
	}

	.button-group {
		display: flex;
		justify-content: space-between;
		align-items: center;
		gap: 1rem;
	}

	.right-buttons {
		display: flex;
		gap: 0.5rem;
	}
</style>

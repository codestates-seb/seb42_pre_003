import { create } from 'zustand';
import { devtools } from 'zustand/middleware';

const useAnsStore = create(
	devtools((set) => ({
		vote: 0,
		plusVote: () => {
			set((state) => ({ vote: state.vote + 1 }));
		},
		minusVote: () => {
			set((state) => ({ vote: state.vote - 1 }));
		},
		book: false,
		handleBook: () => {
			set((state) => ({ book: !state.book }));
		},
		page: 'read',
		handlePage: () => set({ page: 'edit' }),
		answer: '',
		answerBind: (item) => set({ answer: item }),
		answerReset: () => set({ answer: '' }),
		comment: '',
		comBind: (item) => set({ comment: item }),
		comReset: () => set({ comment: '' }),
		edTitle: '',
		edTitleBind: (item) => set({ edTitle: item }),
		edBody: '',
		edBodyBind: (item) => set({ edBody: item }),
		edTag: '',
		edTagBind: (item) => set({ edTag: item }),
		fishies: {},
		fetch: async () => {
			const response = await fetch(
				'http://ec2-52-78-27-218.ap-northeast-2.compute.amazonaws.com:8080//questions?page=1&sort=questionId',
			);
			set({ fishies: await response.json() });
		},
	})),
);

export default useAnsStore;

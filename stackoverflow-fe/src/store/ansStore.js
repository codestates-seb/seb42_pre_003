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
	})),
);

export default useAnsStore;

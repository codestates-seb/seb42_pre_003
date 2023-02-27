import { create } from 'zustand';
import { devtools } from 'zustand/middleware';
import axios from 'axios';

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
		ansList: {},
		getAnswer: async (URL) => {
			const response = await axios.get(URL, {
				Accept: 'application / json',
			});
			set({ ansList: await response.data });
		},
		addAnswer: async (URL, item) => {
			const response = await axios.post(URL, {
				headers: {
					'Content-Type': 'application/json;charset=UTF-8',
					Accept: 'application / json',
				},
				body: JSON.stringify(item),
			});
			set({ ansList: await response.data });
		},
		ansItem: {},
		getAnswerItem: async (URL) => {
			const response = await axios.get(URL, {
				Accept: 'application / json',
			});
			set({ ansItem: await response.data });
		},
		ansDownList: {},
		getAnsDown: async (URL) => {
			const response = await axios.get(URL, {
				Accept: 'application / json',
			});
			set({ ansDownList: await response.data });
		},
		comList: {},
		getCom: async (URL) => {
			const response = await axios.get(URL, {
				Accept: 'application / json',
			});
			set({ comList: await response.data });
		},
	})),
);

export default useAnsStore;

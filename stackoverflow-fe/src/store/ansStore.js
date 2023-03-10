import { create } from 'zustand';
import { devtools } from 'zustand/middleware';
import axios from 'axios';

const token = sessionStorage.getItem('accesstoken');

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
		handlePage: (item) => set({ page: item }),
		answer: '',
		answerBind: (item) => set({ answer: item }),
		answerReset: () => set({ answer: '' }),
		comment: '',
		comBind: (item) => set({ comment: item }),
		comReset: () => set({ comment: '' }),
		edMode: '',
		edModeBind: (item) => set({ edMode: item }),
		edId: '',
		edIdBind: (item) => set({ edId: item }),
		edTitle: '',
		edTitleBind: (item) => set({ edTitle: item }),
		edBody: '',
		edBodyBind: (item) => set({ edBody: item }),
		edTag: '',
		edTagBind: (item) => set({ edTag: item }),
		ansList: [],
		getAnswer: async (URL) => {
			const response = await axios.get(URL, {
				Accept: 'application / json',
			});
			set({ ansList: await response.data });
		},
		addAnswer: async (URL, item) => {
			const response = await axios.post(URL, item, {
				headers: {
					'Content-Type': 'application/json;charset=UTF-8',
					Authorization: `Bearer ${token}`,
					Accept: 'application / json',
				},
			});
			const data = await response.data;
			set((state) => ({ ...state, ansList: [...state.ansList, data] }));
		},
		editAnswer: async (URL, item) => {
			const response = await axios.patch(URL, item, {
				headers: {
					'Content-Type': 'application/json;charset=UTF-8',
					Authorization: `Bearer ${token}`,
					Accept: 'application / json',
				},
			});
			const data = await response.data;
			set((state) => ({
				...state,
				ansList: state.ansList.map((el) => {
					if (el.questionId === data.questionId) {
						return data;
					} else {
						return el;
					}
				}),
			}));
		},
		delAnswer: async (URL) => {
			const response = await axios.delete(URL, {
				headers: {
					Authorization: `Bearer ${token}`,
					Accept: 'application / json',
				},
			});
			const data = await response.data;
			set((state) => ({
				...state,
				ansList: state.ansList.filter(
					(el) => el.questionId !== data.questionId,
				),
			}));
		},
		ansItem: {},
		getAnswerItem: async (URL) => {
			const response = await axios.get(URL, {
				Accept: 'application / json',
			});
			set({ ansItem: await response.data });
		},
		ansDownList: [],
		getAnsDown: async (URL) => {
			const response = await axios.get(URL, {
				Accept: 'application / json',
			});
			set({ ansDownList: await response.data });
		},
		addDown: async (URL, item) => {
			const response = await axios.post(URL, item, {
				headers: {
					'Content-Type': 'application/json;charset=UTF-8',
					Authorization: `Bearer ${token}`,
					Accept: 'application / json',
				},
			});
			const data = await response.data;
			set((state) => ({
				...state,
				ansDownList: [...state.ansDownList, data],
			}));
		},
		editDown: async (URL, item) => {
			const response = await axios.patch(URL, item, {
				headers: {
					'Content-Type': 'application/json;charset=UTF-8',
					Authorization: `Bearer ${token}`,
					Accept: 'application / json',
				},
			});
			const data = await response.data;
			set((state) => ({
				...state,
				ansDownList: state.ansDownList.map((el) => {
					if (el.answerId === data.answerId) {
						return data;
					} else {
						return el;
					}
				}),
			}));
		},
		delDown: async (URL) => {
			const response = await axios.delete(URL, {
				headers: {
					Authorization: `Bearer ${token}`,
					Accept: 'application / json',
				},
			});
			const data = await response.data;
			set((state) => ({
				...state,
				ansDownList: state.ansDownList.filter(
					(el) => el.answerId !== data.answerId,
				),
			}));
		},
		comList: [],
		getCom: async (URL) => {
			const response = await axios.get(URL, {
				Accept: 'application / json',
			});
			set({ comList: await response.data });
		},
		addCom: async (URL, item) => {
			const response = await axios.post(URL, item, {
				headers: {
					'Content-Type': 'application/json;charset=UTF-8',
					Authorization: `Bearer ${token}`,
					Accept: 'application / json',
				},
			});
			const data = await response.data;
			set((state) => ({
				...state,
				comList: [...state.comList, data],
			}));
		},
		editCom: async (URL, item) => {
			const response = await axios.patch(URL, item, {
				headers: {
					'Content-Type': 'application/json;charset=UTF-8',
					Authorization: `Bearer ${token}`,
					Accept: 'application / json',
				},
			});
			const data = await response.data;
			set((state) => ({
				...state,
				comList: state.comList.map((el) => {
					if (el.commentId === data.commentId) {
						return data;
					} else {
						return el;
					}
				}),
			}));
		},
		delCom: async (URL) => {
			const response = await axios.delete(URL, {
				headers: {
					Authorization: `Bearer ${token}`,
					Accept: 'application / json',
				},
			});
			const data = await response.data;
			set((state) => ({
				...state,
				comList: state.comList.filter((el) => el.commentId !== data.commentId),
			}));
		},
	})),
);

export default useAnsStore;

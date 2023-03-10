export function CountUpIcon({ ...values }) {
	return (
		<button {...values}>
			<svg
				aria-hidden='true'
				className='svg-icon iconArrowUpLg'
				width='36'
				height='36'
				viewBox='0 0 36 36'
			>
				<path d='M2 25h32L18 9 2 25Z'></path>
			</svg>
		</button>
	);
}

export function CountDownIcon({ ...values }) {
	return (
		<button {...values}>
			<svg
				aria-hidden='true'
				className='svg-icon iconArrowDownLg'
				width='36'
				height='36'
				viewBox='0 0 36 36'
			>
				<path d='M2 11h32L18 27 2 11Z'></path>
			</svg>
		</button>
	);
}

export function BookMarkIcon({ ...values }) {
	return (
		<button {...values}>
			<svg
				aria-hidden='true'
				className='js-saves-btn-unselected svg-icon iconBookmarkAlt'
				width='18'
				height='18'
				viewBox='0 0 18 18'
			>
				<path d='m9 10.6 4 2.66V3H5v10.26l4-2.66ZM3 17V3c0-1.1.9-2 2-2h8a2 2 0 0 1 2 2v14l-6-4-6 4Z'></path>
			</svg>
		</button>
	);
}

export function BookFullIcon({ ...values }) {
	return (
		<button {...values}>
			<svg
				aria-hidden='true'
				className='fc-theme-primary-500 js-saves-btn-selected svg-icon iconBookmark'
				width='18'
				height='18'
				viewBox='0 0 18 18'
			>
				<path d='M3 17V3c0-1.1.9-2 2-2h8a2 2 0 0 1 2 2v14l-6-4-6 4Z'></path>
			</svg>
		</button>
	);
}

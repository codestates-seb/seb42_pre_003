import { useState } from 'react';

export default function useAskInput(initial) {
	const [value, setValue] = useState(initial);

	const bind = {
		value,
		onChange: (e) => setValue(e.target.value),
	};

	const reset = () => {
		setValue('');
	};

	return [value, bind, reset];
}

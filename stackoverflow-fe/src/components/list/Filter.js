import React from 'react';
import styled from 'styled-components';
import ApplyButton from './ApplyButton';
import CancelButton from './CancelButton';
import SaveButton from './SavsButton';

const FilterContainer = styled.section`
	background-color: #f1f2f3;
	margin: 10px;
	border-radius: 3px;
	border: 1px solid #d6d9dc;

	.filterlist {
		padding: 10px;
		display: flex;
		font-size: 15px;
	}

	.filterlist li {
		margin-right: 10px;
		margin-bottom: 10px;
	}

	.filterlist li > ul {
		margin-top: 10px;
	}

	.filterlist li span {
		font-weight: bold;
	}

	.buttons {
		display: flex;
		align-items: center;
		border-top: 1px solid #d6d9dc;
		padding: 10px;
	}
`;

const Filter = () => {
	return (
		<FilterContainer>
			<ul className='filterlist'>
				<li>
					<span>Filter by</span>
					<ul>
						<li>
							<input type='checkbox' name='filterId' />
							<label>No answers</label>
						</li>
						<li>
							<input type='checkbox' />
							<label>No accepted answer</label>
						</li>
					</ul>
				</li>
				<li>
					<span>Sorted by</span>
					<ul>
						<li>
							<input type='radio' />
							<label>Newest</label>
						</li>
						<li>
							<input type='radio' />
							<label>Recent activity</label>
						</li>
						<li>
							<input type='radio' />
							<label>Highest score</label>
						</li>
					</ul>
				</li>
				<li>
					<span>Sorted by</span>
					<ul>
						<li>
							<input type='radio' />
							<label>My watched tags</label>
						</li>
						<li>
							<input type='radio' />
							<label>The following tags:</label>
						</li>
						<li className='Taginput'>
							<input placeholder='e.g. javascript or python' />
						</li>
					</ul>
				</li>
			</ul>
			<div className='buttons'>
				<ApplyButton></ApplyButton>
				<SaveButton></SaveButton>
				<CancelButton></CancelButton>
			</div>
		</FilterContainer>
	);
};

export default Filter;

import styled from 'styled-components';
import profile from '../img/profile.png';
import posts from '../img/posts.png';
import cake from '../img/cake.png';
import clock from '../img/clock.png';
import date from '../img/date.png';
import pen from '../img/pen.png';
import { useNavigate } from 'react-router-dom';

const Container = styled.div`
	width: 100%;
	max-width: 1100px;
	height: 100%;
	display: flex;
	flex-direction: column;
	padding: 24px;
	border: solid #d6d9dc;
	border-left-width: 1px;
	border-top-width: 0;
	border-bottom-width: 0;
	border-right-width: 0;
`;
const UserBox = styled.div`
	width: 100%;
	display: flex;
	flex-direction: row;
	margin-bottom: 16px;
	margin-top: 10px;
	border-bottom: 1px solid #e4e6e8;
`;
const UserImgBox = styled.div`
	height: 100%;
	display: flex;
	justify-content: center;
	align-items: center;
`;
const UserImg = styled.img`
	width: 128px;
	height: 128px;
	margin: 8px;
	border-radius: 5px;
`;

const UserNameBox = styled.div`
	width: 80%;
	height: 100%;
	display: flex;
	justify-content: center;
	align-items: center;
	margin: 18px;
`;
const UserValue = styled.div`
	width: 100%;
`;
const UserName = styled.p`
	font-size: 30px;
	margin-bottom: 15px;
	margin-top: 20px;
`;
const UserFunction = styled.div`
	font-size: 20px;
	margin-top: 8px;
	display: flex;
	margin-left: -15px;
	align-items: center;
	.middle {
		border-left: 1px solid #6a737c;
		border-right: 1px solid #6a737c;
	}
	.cake {
		width: 18px;
		height: 18px;
	}
	.clock {
		width: 18px;
		height: 18px;
	}
	.date {
		width: 20px;
		height: 20px;
	}
`;
const UserButton = styled.button`
	background: inherit;
	border: none;
	box-shadow: none;
	border-radius: 0;
	overflow: visible;
	color: #6a737c;
	cursor: pointer;
	display: flex;
	align-items: center;
	font-size: 13px;
	margin: 0 5px;
	:hover {
		color: #838c95;
	}
	.icon {
		font-size: 18px;
		margin-left: 6px;
	}
`;

const ProfileHeaderButtonContainer = styled.div`
	display: flex;
	width: 245px;
	justify-content: space-between;
`;

const ProfileHeaderButton = styled.button`
	all: unset;
	box-sizing: border-box;
	display: flex;
	align-items: center;
	width: 120px;
	height: 35px;
	padding: 10px;
	background-color: #ffffff;
	border-radius: 3px;
	border: 1px solid #929eaa;
	color: #6a737c;
	font-size: 13px;
	&:hover {
		cursor: pointer;
		background-color: #f7f9f9;
	}
`;

const ProfileBottomContaner = styled.div`
	width: 100%;
	height: max-content;
	display: flex;
	justify-content: space-between;
`;

const BottomRightContainer = styled.div`
	display: flex;
	flex-direction: column;
	width: 70%;
	height: max-content;
	padding: 12px;
`;

const BottomItemContainer = styled.div`
	display: flex;
	flex-direction: column;
	margin-top: 20px;
	margin-bottom: 20px;
`;

const ItemLabel = styled.label`
	display: block;
	font-size: 22px;
	font-weight: 500;
	margin-bottom: 10px;
	margin-top: 20px;
`;

const ItemCard = styled.div`
	display: flex;
	justify-content: center;
	align-items: center;
	width: 100%;
	height: 120px;
	padding: 12px;
	border: 1px solid #b5b5b5;
	border-radius: 5px;
	box-sizing: border-box;
	background-color: #f6f6f6;
`;

const ItemCards = styled.div`
	display: flex;
	justify-content: center;
	align-items: center;
	width: 100%;
	height: 330px;
	padding: 12px;
	border: 1px solid #b5b5b5;
	border-radius: 5px;
	box-sizing: border-box;
	background-color: #f6f6f6;
`;

const PostsIcon = styled.img`
	width: 200px;
	height: 200px;
	display: flex;
	justify-content: center;
	align-items: center;
	margin-left: 240px;
	margin-bottom: 30px;
	margin-top: 30px;
`;

const Linker = styled.a`
	color: #0074cc;
	font-size: 15px;
	display: inline;
	&:hover {
		color: #49a5f0;
		cursor: pointer;
	}
`;

const Aboutdescription = styled.span`
	color: #6a737c;
	font-size: 15px;
	display: inline;
`;

const Penicon = styled.img`
	width: 20px;
	height: 20px;
`;

const Mypage = () => {
	const navigate = useNavigate();

	return (
		<>
			<Container>
				<UserBox>
					<UserImgBox>
						<UserImg src={profile} alt='useravatar' />
					</UserImgBox>
					<UserNameBox>
						<UserValue>
							<UserName>점메추</UserName>
							<UserFunction>
								<UserButton>
									<UserButton>
										<img src={cake} className='cake' alt='cake' />
									</UserButton>
									Member for 12 days
								</UserButton>
								<div className='middle'></div>
								<UserButton>
									<UserButton>
										<img src={clock} className='clock' alt='clock' />
									</UserButton>
									Last seen this week
								</UserButton>
								<UserButton>
									<UserButton>
										<img src={date} className='date' alt='date' />
									</UserButton>
									Visited 11 days, 9 consecutive
								</UserButton>
							</UserFunction>
						</UserValue>
					</UserNameBox>{' '}
					<ProfileHeaderButtonContainer>
						<ProfileHeaderButton
							onClick={() => {
								navigate('/');
							}}
						>
							<Penicon src={pen} />
							Edit profile
						</ProfileHeaderButton>
					</ProfileHeaderButtonContainer>
				</UserBox>
				<ProfileBottomContaner>
					<BottomRightContainer>
						<BottomItemContainer>
							<ItemLabel>About</ItemLabel>
							<ItemCard
								css={`
									justify-content: center;
									background-color: #f8f9f9;
								`}
							>
								<div
									css={`
										display: inline;
									`}
								>
									<Aboutdescription>
										Your about me section is currently blank. Would you like to
										add one?
									</Aboutdescription>
									<Linker>Edit profile</Linker>
								</div>
							</ItemCard>
						</BottomItemContainer>
						<BottomItemContainer>
							<ItemLabel>Badges</ItemLabel>
							<ItemCard
								css={`
									justify-content: center;
									background-color: #f8f9f9;
								`}
							>
								<div
									css={`
										display: inline;
									`}
								>
									<Aboutdescription>You have not earned any</Aboutdescription>
									<Linker>Edit profile</Linker>
								</div>
							</ItemCard>
						</BottomItemContainer>
						<BottomItemContainer>
							<ItemLabel>Posts</ItemLabel>
							<ItemCards
								css={`
									justify-content: center;
									background-color: #f8f9f9;
								`}
							>
								<div
									css={`
										display: inline;
									`}
								>
									<PostsIcon src={posts} />
									<Aboutdescription>
										Just getting started? Try answering a question! Your most
										helpful questions, answers and tags will appear here. Start
										by <Linker>answering a question</Linker> or{' '}
										<Linker>selecting tags</Linker> that match topics you’re
										interested in.
									</Aboutdescription>
								</div>
							</ItemCards>
						</BottomItemContainer>
					</BottomRightContainer>
				</ProfileBottomContaner>
				{/* </ContentContainer> */}
			</Container>
		</>
	);
};

export default Mypage;

----------------------------
ByteBufHolder				|
----------------------------
	# �̳��� ReferenceCounted
	# ���󷽷�
		ByteBuf content();

		ByteBufHolder copy();

		ByteBufHolder duplicate();

		ByteBufHolder retainedDuplicate();

		@Override
		ByteBufHolder retain();

		@Override
		ByteBufHolder retain(int increment);

		@Override
		ByteBufHolder touch();

		@Override
		ByteBufHolder touch(Object hint);
	
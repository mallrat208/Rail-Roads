package com.mr208.railroads.common.network;

import com.mr208.railroads.RailRoads;
import com.mr208.railroads.common.NetworkHandler;
import com.mr208.railroads.common.entity.EntityRailRiders;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketRailRiders implements IMessage
{
	public PacketRailRiders() {};

	private int x;
	private int y;
	private int z;

	public PacketRailRiders(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public PacketRailRiders(BlockPos pos)
	{
		this.x = pos.getX();
		this.y = pos.getY();
		this.z = pos.getZ();
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
	}

	public static class Handler implements IMessageHandler<PacketRailRiders, IMessage>
	{

		@Override
		public IMessage onMessage(PacketRailRiders message, MessageContext ctx)
		{
			FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
			return null;
		}

		private void handle(PacketRailRiders message, MessageContext ctx)
		{
			EntityPlayerMP playerMP = ctx.getServerHandler().player;

			if(playerMP.isRiding())
				return;

			if(playerMP.hasItemInSlot(EntityEquipmentSlot.FEET) && RailRoads.hasSkateTag(playerMP.getItemStackFromSlot(EntityEquipmentSlot.FEET)))
			{
				BlockPos pos = new BlockPos(message.x, message.y, message.z);
				World world = playerMP.world;

				pos = findRailBlockPos(world, pos);

				if(pos != null)
				{
					IBlockState state = world.getBlockState(pos);

					BlockRailBase.EnumRailDirection railDirection = state.getBlock() instanceof BlockRailBase ? ((BlockRailBase) state.getBlock()).getRailDirection(world, pos, state, null) : BlockRailBase.EnumRailDirection.NORTH_SOUTH;
					double offsetY = 0.1D;

					if (railDirection.isAscending())
						offsetY = 0.6D;

					EntityRailRiders cart = new EntityRailRiders(world,
							pos.getX() + 0.5,
							pos.getY() + 0.0625D + offsetY,
							pos.getZ() + 0.5);

					cart.setDropItemsWhenDead(false);

					world.spawnEntity(cart);
					playerMP.startRiding(cart, true);
					cart.updatePassenger(playerMP);
				}
			}

			PacketRailRidersCooldown packet = new PacketRailRidersCooldown();
			NetworkHandler.INSTANCE.sendTo(packet,playerMP);
		}
	}

	private static BlockPos findRailBlockPos(World worldIn, BlockPos posIn)
	{
		if(BlockRailBase.isRailBlock(worldIn, posIn))
			return posIn;
		if(BlockRailBase.isRailBlock(worldIn, posIn.down()))
			return posIn.down();
		if(BlockRailBase.isRailBlock(worldIn, posIn.north()))
			return posIn.north();
		if(BlockRailBase.isRailBlock(worldIn, posIn.north().down()))
			return posIn.north().down();
		if(BlockRailBase.isRailBlock(worldIn, posIn.west()))
			return posIn.west();
		if(BlockRailBase.isRailBlock(worldIn, posIn.west().down()))
			return posIn.west().down();
		if(BlockRailBase.isRailBlock(worldIn, posIn.east()))
			return posIn.east();
		if(BlockRailBase.isRailBlock(worldIn, posIn.east().down()))
			return posIn.east().down();
		if(BlockRailBase.isRailBlock(worldIn, posIn.south().down()))
			return posIn.south().down();
		if(BlockRailBase.isRailBlock(worldIn, posIn.south().down()))
			return posIn.south().down();

		return null;
	}
}

package tutoriel.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelCupboard extends ModelBase
{
	ModelRenderer behind;
	ModelRenderer left;
	ModelRenderer right;
	ModelRenderer bottom;
	ModelRenderer top;
	ModelRenderer middle;
	ModelRenderer doorLeft;
	ModelRenderer doorLink;

	public ModelCupboard()
	{
		textureWidth = 64;
		textureHeight = 32;

		behind = new ModelRenderer(this, 0, 0);
		behind.addBox(0F, 0F, 0F, 1, 16, 16);
		behind.setRotationPoint(7F, 8F, -8F);
		behind.setTextureSize(64, 32);
		behind.mirror = true;
		setRotation(behind, 0F, 0F, 0F);
		left = new ModelRenderer(this, 0, 0);
		left.addBox(0F, 0F, 0F, 15, 16, 1);
		left.setRotationPoint(-8F, 8F, 7F);
		left.setTextureSize(64, 32);
		left.mirror = true;
		setRotation(left, 0F, 0F, 0F);
		right = new ModelRenderer(this, 0, 0);
		right.addBox(0F, 0F, 0F, 15, 16, 1);
		right.setRotationPoint(-8F, 8F, -8F);
		right.setTextureSize(64, 32);
		right.mirror = true;
		setRotation(right, 0F, 0F, 0F);
		bottom = new ModelRenderer(this, 0, 0);
		bottom.addBox(0F, 0F, 0F, 16, 1, 16);
		bottom.setRotationPoint(-8F, 23F, -8F);
		bottom.setTextureSize(64, 32);
		bottom.mirror = true;
		setRotation(bottom, 0F, 0F, 0F);
		top = new ModelRenderer(this, 0, 0);
		top.addBox(0F, 0F, 0F, 16, 1, 16);
		top.setRotationPoint(-8F, 8F, -8F);
		top.setTextureSize(64, 32);
		top.mirror = true;
		setRotation(top, 0F, 0F, 0F);
		middle = new ModelRenderer(this, 0, 0);
		middle.addBox(0F, 0F, 0F, 14, 1, 14);
		middle.setRotationPoint(-6.6F, 15F, -7F);
		middle.setTextureSize(64, 32);
		middle.mirror = true;
		setRotation(middle, 0F, 0F, 0F);
		doorLeft = new ModelRenderer(this, 0, 0);
		doorLeft.addBox(0F, -6F, 0F, 1, 14, 8);
		doorLeft.setRotationPoint(-6.9F, 15F, 8F);
		doorLeft.setTextureSize(64, 32);
		doorLeft.mirror = true;
		setRotation(doorLeft, 0F, 3.141593F, 0F);
		doorLink = new ModelRenderer(this, 46, 10);
		doorLink.addBox(0F, -6F, 0F, 1, 14, 8);
		doorLink.setRotationPoint(-7.9F, 15F, -8F);
		doorLink.setTextureSize(64, 32);
		doorLink.mirror = true;
		setRotation(doorLink, 0F, 0F, 0F);
	}

	public void render(float f)
	{
		behind.render(f);
		left.render(f);
		right.render(f);
		bottom.render(f);
		top.render(f);
		middle.render(f);
		doorLeft.render(f);
		doorLink.render(f);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
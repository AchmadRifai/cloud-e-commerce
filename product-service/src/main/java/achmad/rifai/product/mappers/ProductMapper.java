package achmad.rifai.product.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import achmad.rifai.product.models.Product;
import achmad.rifai.product.pojo.ProductChangedReq;
import achmad.rifai.product.pojo.ProductShowRes;

@Mapper
public interface ProductMapper {

	ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

	Product productChangedReqToProduct(ProductChangedReq req);

	ProductShowRes productToProductShowRes(Product product);

}

/* This file is auto generated.
 * DO NOT EDIT IT MANUALY!
 */

#ifndef INCLUDE_ANGLE_TAGS_H_
#define INCLUDE_ANGLE_TAGS_H_

#include <ratio>
#include <string>

namespace units {
namespace angle {
namespace tags
{

struct angle_tag { using code = std::ratio<3, 1>; };

struct mils_tag
{
	static constexpr double scale = 0.05625;
	static std::string singularName();
	static std::string pluralName();
};
struct degrees_tag
{
	static constexpr double scale = 1.0;
	static std::string singularName();
	static std::string pluralName();
};
struct radians_tag
{
	static constexpr double scale = 57.2957795131;
	static std::string singularName();
	static std::string pluralName();
};


}
}
}

#endif /* INCLUDE_ANGLE_TAGS_H_ */